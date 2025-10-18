"use client";

import { useEffect, useState } from "react";
import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { AlertCircle, MessageSquare, User } from "lucide-react";
import { CommentsList } from "@/components/comments-list";
import { Separator } from "@/components/ui/separator";
import { ServiceToggle } from "@/components/service-toggle";

interface Comment {
  author: string;
  text: string;
  timestamp: Date;
}

interface Publication {
  id: string;
  title: string;
  text: string;
  imageUrl: string;
  author: string;
  comments: Comment[];
}

interface PublicationDetailProps {
  id: string;
}

export function PublicationDetail({ id }: PublicationDetailProps) {
  const [publication, setPublication] = useState<Publication | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [showAuthor, setShowAuthor] = useState(false);
  const [showComments, setShowComments] = useState(false);

  useEffect(() => {
    fetchPublication();
  }, [id]);

  const fetchPublication = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await fetch(
        `http://localhost:8080/api/v1/publications/${id}`
      );

      if (!response.ok) {
        throw new Error("Failed to fetch publication");
      }

      const data = await response.json();
      setPublication(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : "An error occurred");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="mx-auto max-w-4xl">
        <Card>
          <Skeleton className="h-96 w-full rounded-t-lg" />
          <CardHeader>
            <Skeleton className="h-8 w-3/4" />
          </CardHeader>
          <CardContent className="space-y-4">
            <Skeleton className="h-4 w-full" />
            <Skeleton className="h-4 w-full" />
            <Skeleton className="h-4 w-2/3" />
          </CardContent>
        </Card>
      </div>
    );
  }

  if (error || !publication) {
    return (
      <div className="mx-auto max-w-4xl">
        <Alert variant="destructive">
          <AlertCircle className="h-4 w-4" />
          <AlertDescription>
            {error || "Publication not found"}
          </AlertDescription>
        </Alert>
      </div>
    );
  }

  return (
    <div className="mx-auto max-w-4xl space-y-6">
      <ServiceToggle />

      <Card className="overflow-hidden">
        <div className="aspect-[21/9] overflow-hidden bg-muted">
          <img
            src={
              publication.imageUrl ||
              "/placeholder.svg?height=600&width=1200&query=publication-hero"
            }
            alt={publication.title}
            className="h-full w-full object-cover"
          />
        </div>
        <CardHeader>
          <h1 className="text-4xl font-bold tracking-tight text-balance">
            {publication.title}
          </h1>
        </CardHeader>
        <CardContent className="space-y-6">
          <p className="text-lg leading-relaxed text-foreground">
            {publication.text}
          </p>

          <Separator />

          <div className="flex gap-3">
            <Button
              variant={showAuthor ? "default" : "outline"}
              onClick={() => setShowAuthor(!showAuthor)}
              className="gap-2"
            >
              <User className="h-4 w-4" />
              {showAuthor ? "Hide" : "Show"} Author
            </Button>
            <Button
              variant={showComments ? "default" : "outline"}
              onClick={() => setShowComments(!showComments)}
              className="gap-2"
            >
              <MessageSquare className="h-4 w-4" />
              {showComments ? "Hide" : "Show"} Comments
            </Button>
          </div>

          {showAuthor && (
            <Card className="bg-accent">
              <CardContent className="pt-6">
                <div className="flex items-center gap-3">
                  <div className="flex h-12 w-12 items-center justify-center rounded-full bg-primary text-primary-foreground">
                    <User className="h-6 w-6" />
                  </div>
                  <div>
                    <p className="text-sm font-medium text-muted-foreground">
                      Written by
                    </p>
                    <p className="text-xl font-semibold">
                      {publication.author}
                    </p>
                  </div>
                </div>
              </CardContent>
            </Card>
          )}

          {showComments && (
            <CommentsList
              publicationId={publication.id}
              comments={publication.comments || []}
            />
          )}
        </CardContent>
      </Card>
    </div>
  );
}
