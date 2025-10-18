"use client"

import { useEffect, useState } from "react"
import Link from "next/link"
import { Card, CardContent, CardFooter, CardHeader } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Skeleton } from "@/components/ui/skeleton"
import { AlertCircle } from "lucide-react"
import { Alert, AlertDescription } from "@/components/ui/alert"

interface Publication {
  id: string
  title: string
  text: string
  imageUrl: string
  author: string
}

export function PublicationsList() {
  const [publications, setPublications] = useState<Publication[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    fetchPublications()
  }, [])

  const fetchPublications = async () => {
    try {
      setLoading(true)
      setError(null)
      const response = await fetch("http://localhost:8080/api/v1/publications")

      if (!response.ok) {
        throw new Error("Failed to fetch publications")
      }

      const data = await response.json()
      setPublications(data)
    } catch (err) {
      setError(err instanceof Error ? err.message : "An error occurred")
    } finally {
      setLoading(false)
    }
  }

  if (loading) {
    return (
      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        {[...Array(6)].map((_, i) => (
          <Card key={i} className="overflow-hidden">
            <Skeleton className="h-48 w-full" />
            <CardHeader>
              <Skeleton className="h-6 w-3/4" />
            </CardHeader>
            <CardContent>
              <Skeleton className="h-4 w-full" />
              <Skeleton className="mt-2 h-4 w-5/6" />
            </CardContent>
          </Card>
        ))}
      </div>
    )
  }

  if (error) {
    return (
      <Alert variant="destructive">
        <AlertCircle className="h-4 w-4" />
        <AlertDescription>{error}</AlertDescription>
      </Alert>
    )
  }

  if (publications.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center py-12 text-center">
        <p className="text-lg text-muted-foreground">No publications yet</p>
        <p className="mt-2 text-sm text-muted-foreground">Create your first post to get started</p>
        <Link href="/new">
          <Button className="mt-4">Create Post</Button>
        </Link>
      </div>
    )
  }

  return (
    <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
      {publications.map((publication) => (
        <Link key={publication.id} href={`/publications/${publication.id}`}>
          <Card className="group overflow-hidden transition-all hover:shadow-lg">
            <div className="aspect-video overflow-hidden bg-muted">
              <img
                src={publication.imageUrl || "/placeholder.svg?height=300&width=400&query=publication"}
                alt={publication.title}
                className="h-full w-full object-cover transition-transform group-hover:scale-105"
              />
            </div>
            <CardHeader>
              <h3 className="line-clamp-2 text-xl font-semibold text-balance">{publication.title}</h3>
            </CardHeader>
            <CardContent>
              <p className="line-clamp-3 text-sm text-muted-foreground leading-relaxed">{publication.text}</p>
            </CardContent>
            <CardFooter>
              <p className="text-xs text-muted-foreground">By {publication.author}</p>
            </CardFooter>
          </Card>
        </Link>
      ))}
    </div>
  )
}
